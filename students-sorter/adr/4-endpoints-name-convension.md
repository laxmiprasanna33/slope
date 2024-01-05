## ADR-0001: Framework Selection

### Context

We need to select application architecture for `endpoints naming convention`.

### Decision

I will use RPC style endpoints.

### Reasoning

I selected RPC style, as I'm performing multiple operations under single endpoint, which fits better for RPC function
call.

Ideally I could split these action into two RESTful endpoints:
POST `/students` to add students into data source (e.g internal database)
GET `/students?sort={field}` to fetch students sorted by field

