# Quarkus Multi-tenant POC with DBOS Transact

A proof-of-concept application demonstrating multi-tenancy in Quarkus with DBOS Transact integration for reliable workflow execution.

## Features

- **Multi-tenant Architecture**: Database-per-tenant isolation using Quarkus datasources
- **DBOS Transact Integration**: Reliable workflow execution with automatic retries and durability
- **RESTful APIs**: Client and address management endpoints
- **Hibernate ORM**: Entity management with Panache repositories
- **MySQL Support**: Multi-database configuration for tenant separation

## Architecture

The application implements a database-per-tenant multi-tenancy model where:
- Each tenant has a separate MySQL database (c1, c2, etc.)
- Tenant resolution is handled via HTTP headers
- DBOS Transact ensures reliable workflow execution across tenant operations

## Prerequisites

- Java 21
- Maven 3.8+
- MySQL 8.0+
- Docker (optional, for MySQL setup)

## Database Setup

1. Create MySQL databases for each tenant:
```sql
CREATE DATABASE c1;
CREATE DATABASE c2;
```

2. Create a MySQL user:
```sql
CREATE USER 'lp_user'@'%' IDENTIFIED BY 'lp_user';
GRANT ALL PRIVILEGES ON c1.* TO 'lp_user'@'%';
GRANT ALL PRIVILEGES ON c2.* TO 'lp_user'@'%';
FLUSH PRIVILEGES;
```

3. Run the table creation script in each database:
```bash
mysql -u lp_user -p c1 < tables.sql
mysql -u lp_user -p c2 < tables.sql
```

## Configuration

The application is configured for multi-tenant operation in `application.properties`:

- **Default datasource**: Points to c1 database
- **Named datasources**: c1 and c2 for different tenants
- **DBOS logging**: Enabled for workflow debugging

## Running the Application

### Development Mode
```bash
mvn quarkus:dev
```

### Production Build
```bash
mvn clean package
java -jar target/multiTenantPOC-dev.jar
```

## API Usage


### Execute Workflow
```bash
curl -H "X-Tenant-ID: c1" http://localhost:8080/workflow/invoke
```

## Project Structure

```
src/main/java/com/example/dbos/quarkus/poc/
├── domain/                 # Entity classes
│   ├── RefClient.java
│   └── RefClientAddress.java
├── repositories/           # Data access layer
│   ├── ClientDataService.java
│   ├── RefClientRepository.java
│   └── RefClientAddressRepository.java
├── workflow/              # DBOS workflow components
│   ├── WorkflowInterface.java
│   ├── WorkflowResource.java
│   └── WorkflowService.java
├── DbosConfig.java        # DBOS configuration
├── HibernateTenantResolver.java  # Multi-tenant resolver
├── Main.java             # Application entry point
└── TenantContext.java    # Tenant context management
```

## Key Components

- **TenantContext**: Manages tenant-specific context throughout request lifecycle
- **HibernateTenantResolver**: Resolves tenant datasources based on HTTP headers
- **WorkflowService**: DBOS Transact workflows for reliable data operations
- **ClientDataService**: Business logic for client and address operations

## Multi-tenancy Implementation

The application uses Quarkus's multi-datasource capability:
1. Tenant ID is extracted from `X-Tenant-ID` header
2. `HibernateTenantResolver` maps tenant ID to appropriate datasource
3. All database operations are automatically routed to tenant-specific database

## DBOS Transact Integration

DBOS Transact provides:
- **Reliability**: Automatic retries on failures
- **Durability**: Workflow state persistence
- **Observability**: Built-in logging and tracing

## Development Notes

- Uses Java 21 and Quarkus 3.6.4
- Hibernate ORM with Panache for simplified data access
- RESTEasy Jackson for JSON serialization
- File-based logging enabled for debugging# QuarkusMultitenantPOC
# QuarkusMultitenantPOC
