package app.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElasticConfiguration extends ElasticsearchConfiguration {

    @Value("${elasticsearch.username}")
    private String elasticUsername;
    @Value("${elasticsearch.password}")
    private String elasticPassword;
    @Value("${elasticsearch.host}")
    private String elasticUrl;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elasticUrl)
                .withBasicAuth(elasticUsername, elasticPassword)
                .build();
    }
}
