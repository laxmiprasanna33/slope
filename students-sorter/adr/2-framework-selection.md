## ADR-0001: Framework Selection

### Context

I could try to start doing it frameworkless, but due to the fact that time is limited I'm sticking to specific framework from the scratch.


### Decision

I will use Micronaut as the primary web framework for the project.

### Reasoning

The choice of web framework is not critical for this project. 
However, I chose Micronaut, because Spring Boot sounds like an overkill.

I've considered other options such as Quarkus, Helidon, Ratpack and Javalin, Spark, but at the end just wanted to try Micronaut.

### Consequences

The use of Micronaut will likely make development faster and easier, given its small footprint, high performance, and easy testing capability.