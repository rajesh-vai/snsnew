package core;

import search.ISynthesis;
import search.IAction;
import search.IQueryConstruction;
import dataprocessor.ISNSCoreData;
import dataprocessor.Noise;
import dataprocessor.Synonyms;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import utils.DbUtils;
import utils.FileUtils;

/**
 * Created by Rajesh on 17-02-2018.
 */

@Component
public class Wiring {

    @Bean
    ISynthesis objISynthesis() {
        return new ISynthesis();
    }

    @Bean
    IAction objIAction() {
        return new IAction();
    }
    @Bean
    IQueryConstruction objIQueryConstruction() {
        return new IQueryConstruction();
    }

    @Bean
    ISNSCoreData objISNSCoreData() {
        return new ISNSCoreData();
    }

    @Bean
    DbUtils objDbUtils() {
        return new DbUtils();
    }

    @Bean
    FileUtils objFileUtils() {
        return new FileUtils();
    }
    @Bean
    Synonyms objSynonyms() {
        return new Synonyms();
    }

    @Bean
    Noise objNoise() {
        return new Noise();
    }

}
