package mktany2k.wcc;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import mktany2k.wcc.model.Location;
import mktany2k.wcc.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {


    private final Resource csv;


    private final LocationRepository repository;

    public DatabaseInitializer(
            LocationRepository repository,
            @Value("classpath:ukpostcodes.csv") Resource csv) {
        this.repository = repository;
        this.csv = csv;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        long count = repository.count();
        if (count > 0) {
            System.err.println("database initialized with total of " + count + " records");
            return;
        }
        System.err.println("populate data");
        CsvSchema headerSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        try {
            MappingIterator<Location> it = mapper.readerFor(Location.class)
                    .with(headerSchema)
                    .readValues(csv.getFile());
            List<Location> locations = it.readAll();
            System.err.println("total records: " + locations.size());
            long start = System.currentTimeMillis();
            repository.saveAllAndFlush(locations);
            long end = System.currentTimeMillis();
            System.err.println("Total time taken is " + (end - start) + " milliseconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
