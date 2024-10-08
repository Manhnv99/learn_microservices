//package home.microservices.api_gateway.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RouteCustomConfig {
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("r1", r -> r.host("**.baeldung.com")
//                        .and()
//                        .path("/baeldung")
//                        .uri("http://baeldung.com"))
//                .route("myOtherID", r -> r.host("**.baeldung.com")
//                        .and()
//                        .path("/myOtherRouting")
//                        .filters(f -> f.prefixPath("/myPrefix"))
//                        .uri("http://othersite.com"))
//                .build();
//    }
//
//}
