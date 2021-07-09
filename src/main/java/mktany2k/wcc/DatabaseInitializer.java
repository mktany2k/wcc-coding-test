package mktany2k.wcc;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import mktany2k.wcc.model.Location;
import mktany2k.wcc.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
            logger.info("database already initialized with {} of records", count);
            return;
        }
        logger.info("populating location data into for the first time");
        CsvSchema headerSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        try {
            MappingIterator<Location> it = mapper.readerFor(Location.class)
                    .with(headerSchema)
                    .readValues(csv.getFile());
            repository.saveAllAndFlush(it.readAll());
        } catch (IOException e) {
            logger.error("Error occurred while populating initial data into database", e);
        }
    }
}
