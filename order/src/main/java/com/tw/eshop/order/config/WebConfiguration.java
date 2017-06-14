package com.tw.eshop.order.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

/**
 * Created by qbhuang on 14/06/2017.
 * keytool -genkey -alias tomcat -keyalg RSA
 * SeeAlso: http://www.jianshu.com/p/c3879add87ec
 */
@Configuration
@PropertySource("classpath:/tomcat.https.properties")
@EnableConfigurationProperties(WebConfiguration.TomcatSslConnectorProperties.class)
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public EmbeddedServletContainerFactory servletContainer(TomcatSslConnectorProperties properties) {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector(properties));
        return tomcat;
    }

    private Connector createSslConnector(TomcatSslConnectorProperties properties) {
        Connector connector = new Connector();
        properties.configureConnector(connector);
        return connector;
    }

    @ConfigurationProperties(prefix = "custom.tomcat.https")
    public static class TomcatSslConnectorProperties {
        private Integer port;
        private Boolean ssl = true;
        private Boolean secure = true;
        private String scheme = "https";
        private File keystore;
        private String keystorePassword;
        //这里为了节省空间，省略了getters和setters，读者在实践的时候要加上

        public void configureConnector(Connector connector) {
            if (port != null) {
                connector.setPort(port);
            }
            if (secure != null) {
                connector.setSecure(secure);
            }
            if (scheme != null) {
                connector.setScheme(scheme);
            }
            if (ssl != null) {
                connector.setProperty("SSLEnabled", ssl.toString());
            }
            if (keystore != null && keystore.exists()) {
                connector.setProperty("keystoreFile", keystore.getAbsolutePath());
                connector.setProperty("keystorePassword", keystorePassword);
            }
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public Boolean getSsl() {
            return ssl;
        }

        public void setSsl(Boolean ssl) {
            this.ssl = ssl;
        }

        public Boolean getSecure() {
            return secure;
        }

        public void setSecure(Boolean secure) {
            this.secure = secure;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public File getKeystore() {
            return keystore;
        }

        public void setKeystore(File keystore) {
            this.keystore = keystore;
        }

        public String getKeystorePassword() {
            return keystorePassword;
        }

        public void setKeystorePassword(String keystorePassword) {
            this.keystorePassword = keystorePassword;
        }
    }
}
