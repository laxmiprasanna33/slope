## ADR-0001: Framework Selection

### Context

We need to select application architecture for `students-sorter` service.

### Decision

I will use Hexagonal Architecture (ports and adapters).

### Reasoning

I selected ports & adapters, because it fits ideally for the use case of multiple algorithms easily extendable in the future.

I've considered classic 3 layers, just because it's short recruitment task, but 

### Consequences

Higher entry threshold for novices, but it will be easily extendable in the future.