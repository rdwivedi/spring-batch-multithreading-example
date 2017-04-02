package com.ezcode.quantvalley.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.ezcode.quantvalley.batch.model.FxMarketEvent;

/**
 * The Class FxMarketEventReader.
 *
 * @author rdwivedi
 */
public class FxMarketEventReader extends FlatFileItemReader<FxMarketEvent> {

	public FxMarketEventReader() {
		//Set input file
		this.setResource(new ClassPathResource("trades.csv"));
		//Skip the file header line
		this.setLinesToSkip(1);
		//Line is mapped to item (FxMarketEvent) using setLineMapper(LineMapper)
		this.setLineMapper(new DefaultLineMapper<FxMarketEvent>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "stock", "time", "price", "shares" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<FxMarketEvent>() {
					{
						setTargetType(FxMarketEvent.class);
					}
				});
			}
		});
	}

}
