package com.ezcode.quantvalley.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.ezcode.quantvalley.batch.listener.JobCompletionNotificationListener;
import com.ezcode.quantvalley.batch.model.FxMarketEvent;
import com.ezcode.quantvalley.batch.model.FxMarketVolumeStore;
import com.ezcode.quantvalley.batch.model.Trade;
import com.ezcode.quantvalley.batch.processor.FxMarketEventProcessor;
import com.ezcode.quantvalley.batch.reader.FxMarketEventReader;
import com.ezcode.quantvalley.batch.writer.StockVolumeAggregator;

/**
 * The Class BatchConfiguration.
 * 
 * @author rdwivedi
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public FxMarketVolumeStore fxMarketPricesStore() {
		return new FxMarketVolumeStore();
	}

	// FxMarketEventReader (Reader)
	@Bean
	public FxMarketEventReader fxMarketEventReader() {
		return new FxMarketEventReader();
	}

	// FxMarketEventProcessor (Processor)
	@Bean
	public FxMarketEventProcessor fxMarketEventProcessor() {
		return new FxMarketEventProcessor();
	}

	// StockVolumeAggregator (Writer)
	@Bean
	public StockVolumeAggregator stockVolumeAggregator() {
		return new StockVolumeAggregator();
	}

	// JobCompletionNotificationListener (File loader)
	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}

	// Configure job step
	@Bean
	public Job fxMarketPricesETLJob() {
		return jobBuilderFactory.get("FxMarket Volume ETL Job").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(etlStep()).end().build();
	}
	
	@Bean
	public TaskExecutor taskExecutor(){
	    SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");
	    asyncTaskExecutor.setConcurrencyLimit(5);
	    return asyncTaskExecutor;
	}
    
	@Bean
	public Step etlStep() {
		return stepBuilderFactory.get("Extract -> Transform -> Aggregate -> Load").<FxMarketEvent, Trade> chunk(10000)
				.reader(fxMarketEventReader()).processor(fxMarketEventProcessor())
				.writer(stockVolumeAggregator())
				.taskExecutor(taskExecutor()).build();
	}

}
