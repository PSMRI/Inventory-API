# CLAUDE.md - Inventory-API

## Project Overview

Inventory-API is a Spring Boot backend service for the AMRIT platform that manages facility-level inventory operations. It handles stock entry, stock exit, stock adjustment, dispensing against prescriptions, indent/transfer management, patient returns, and inventory reporting.

## Tech Stack

- Java 17, Spring Boot 3.2.2, Spring Data JPA, Hibernate 6.4.1
- MySQL 8.0 (via mysql-connector-j)
- Redis (session management)
- MapStruct (object mapping), Lombok
- Swagger/OpenAPI (springdoc-openapi)
- WAR packaging for Wildfly deployment
- JaCoCo (coverage), Checkstyle (style)

## Build & Run

```bash
mvn clean install -DENV_VAR=local          # Build
mvn spring-boot:run -DENV_VAR=local        # Run locally
mvn -B package --file pom.xml -P <profile> # Package WAR (dev, local, test, ci, uat)
mvn test                                    # Run tests
```

Environment is set via `-DENV_VAR=<env>` which selects `common_<env>.properties`.

## Package Structure

Base package: `com.iemr.inventory`

| Package | Purpose |
|---------|---------|
| `controller/` | REST controllers (one per domain) |
| `service/` | Business logic layer (interface + impl pattern) |
| `repo/` and `repository/` | JPA repositories (split across two packages) |
| `data/` | JPA entity classes |
| `mapper/` | MapStruct mappers (primarily for reports) |
| `model/` | DTOs/view models (reports) |
| `config/` | Spring configuration |
| `utils/` | Cross-cutting: Redis, HTTP clients, response wrappers, exception handling |

## Key Domains / Controllers

- **StockEntryController** - Receiving stock into facility
- **StockExitController** - Issuing stock out of facility
- **StockAdjustmentController** - Inventory adjustments
- **IndentController** - Indent requests and transfers between facilities
- **ItemController** - Item master management
- **ItemfacilitymappingController** - Mapping items to facilities
- **StoreController** - Store/facility management
- **SupplierMasterController** - Supplier management
- **ManufacturerController** - Manufacturer management
- **PatientReturnController** - Handling patient medicine returns
- **DispenseAgainstRxController** (in `dispenseagainst_rx`) - Dispensing against prescriptions
- **CRMReportController** - Inventory reports
- **PharmacologicalCategoryController**, **DrugtypeController**, **UnitOfMeasurementController**, **FacilitytypeController** - Master data
- **VersionController** - API version info

## Architecture Notes

- Standard layered architecture: Controller -> Service -> Repository -> Entity
- Two repository packages exist (`repo/` and `repository/`) - likely historical; both contain JPA interfaces
- Redis used for session validation via `utils/redis/`
- HTTP utility classes in `utils/http/` for inter-service communication
- Response wrapper pattern in `utils/response/`
- Gateway utilities in `utils/gateway/` for email integration
- MapStruct mappers used primarily in the report module
