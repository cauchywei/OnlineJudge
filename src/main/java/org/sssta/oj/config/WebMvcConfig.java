package org.sssta.oj.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cauchywei on 15/10/17.
 */
//@Configuration
//@EnableConfigurationProperties({ ResourceProperties.class })
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Autowired
//    private ResourceProperties resourceProperties = new ResourceProperties();
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        Integer cachePeriod = resourceProperties.getCachePeriod();
//
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/")
//                .setCachePeriod(cachePeriod);
//
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/index.html")
//                .setCachePeriod(cachePeriod).resourceChain(true)
//                .addResolver(new PathResourceResolver() {
//                    @Override
//                    protected Resource getResource(String resourcePath,
//                                                   Resource location) throws IOException {
//                        return location.exists() && location.isReadable() ? location
//                                : null;
//                    }
//                });
//    }
}