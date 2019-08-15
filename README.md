![Epsilon-Sigma-Lambda](/epsilon-sigma-lambda.png?raw=true)

# Epsilon Sigma Lambda
Epsilon Sigma Lambda provides a Model Builder API to ease configuration and creation of models used by Epsilon scripts when running in non-eclipse environments.


## Supported versions

epsilon-sigma-executors | Epsilon   | 
------------------------|-----------|
2.0.0                   | 1.6.x     |
1.0.0                   | 1.5.x     |

## Installation

Using your prefered dependency manager:

### Maven

```
<dependencies>
	<dependency>
   		<groupId>org.eclipse.epsilon.labs</groupId>
		<artifactId>epsilon-sigma-builders</artifactId>
		<version>2.0.0-SNAPSHOT</version><!--bump-->
	</dependency>
	<!-- Add specific model builders as required -->
	<dependency>
   		<groupId>org.eclipse.epsilon.labs</groupId>
		<artifactId>epsilon-sigma-builders-emf</artifactId>
		<version>2.0.0-SNAPSHOT</version><!--bump-->
	</dependency>
	<dependency>
   		<groupId>org.eclipse.epsilon.labs</groupId>
		<artifactId>epsilon-sigma-builders-simulink</artifactId>
		<version>2.0.0-SNAPSHOT</version><!--bump-->
	</dependency>
	<dependency>
   		<groupId>org.eclipse.epsilon.labs</groupId>
		<artifactId>epsilon-sigma-builders-spreadsheets</artifactId>
		<version>2.0.0-SNAPSHOT</version><!--bump-->
	</dependency>
</dependencies>
```

For other managers, change the version to the correct one:

### Apache Ivy

```
<dependency org="org.eclipse.epsilon.labs" name="epsilon-sigma-executors" rev="xxx" />
```

### Groovy Grape

```
@Grapes( 
@Grab(group='org.eclipse.epsilon.labs', module='epsilon-sigma-executors', version='xxx') 
)
```

### Gradle/Grails

```
compile 'org.eclipse.epsilon.labs:epsilon-sigma-executors:xxx'
```

### Manually

Alternatively, you can download and add the jars manually to your project. You can find the executable, sources and javadoc jars here: `http://repo1.maven.org/maven2/org/eclipse/epsilon/labs/epsilon-sigma-builders/`


If you want to work with the SNAPSHOT versions, you need to add the OSS Sonatype repository to your pom:

```
<repositories>
	...
	<repository>
   		<id>oss-sonatype</id>
		<name>oss-sonatype</name>
		<url>https://oss.sonatype.org/content/repositories/snapshots/</url>
		<snapshots>
			<enabled>true</enabled>
		</snapshots>
		</repository>
	</repositories>
	...
<repositories>	
```

## The Epsilon Model Connectivity layer

> To abstract away from diverse model representations and APIs provided by different modelling technologies, EMC defines the IModel interface. IModel provides a number of methods that enable querying and modifying the model elements it contains at a higher level of abstraction. 

When inside an Eclipse environment, the different launch configuration dialogs for the Epsilon languages allow users to define what models to use when running an Epsilon script.
For each kind of model a different set of settings must be provided.
Some of these features are shared amongst all models.

The ModelBuilder API aims at providing a pure Java alternative to the launch configuration model settings dialogs.
The API is designed to use the same default values as the launch configuration dialogs and wraps some of the IModel configuration API to simplify the process of creating and configuring IModels to use in non-eclipse environments.

## ModelBuilder API

All model builders provide a core API to set the *name*, *aliases* and *path* to the model. The core API also allows defining the *read on load* and *store on disposal* flags.

Builders for specific model types extend this basic API to provide additional methods to set attributes and/or flags that are required by the specific type.
For examples, the EMF builder would allow the users to set the location/URI of the model's metamodel, and the Simulink builder allows setting the path to the Matlab Engine's jar.

The following snippet shows how an EMF model is built using the EMF builder.

```
IModel modelA = new EmfModelBuilder()
		.withName("ModelA")
		.readOnLoad(true)
		.withModelPath("some/path")
		.useCache(false)
		.withMetamodelUri("http://sime/uri")
		.build();
```
Later, you can add *modelA* to the models used by an Epsilon engine.
Look at [Epsilon-Executors](https://github.com/epsilonlabs/Epsilon-Executors) for a related API that can be used to run Epsilon scripts from non-eclipse environments.

## Using the builders

Our Wiki provides detailed information on how to use the different builders.

## Developing a new builder

If you want to provide your own builder (e.g. for a model kind not supported here) the best place to start is in the *core* module to understand the base API. 
Next, look at existing implementations to see how it can be implemented.
Finally, if you want to share your builder you can follow the project's CONTRIBUTIONS and submit a pull request.