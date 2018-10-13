# Uniwear Batch CLI

Artifact JAR serves as the main java entry point.

## Source Directories

- [project](project)
	- Mapforce Project.
	- Not integrated with Maven.
- src 
	- [gen](src/gen)
		- MapForce generated code.
		- Pre-generated code is unmodified.
	- [main](src/main)
		- CLI Option parsing, Logging, and invoking generated functions.
		- Pre-Generated Functions are invoked via reflection.