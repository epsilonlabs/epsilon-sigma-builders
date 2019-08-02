# Versioning 

We use semantic versioning and SNAPSHOT as qualifier for the latest development version.

Tags are named v.\[version\].


# Branching

We follow git-flow practices. There are two main branches:

- master: stable releases (i.e. versions with no qualifiers)
- develop: latest development version (i.e. SNAPSHOT qualifier)

When preparing for a release, a release candidate branch under `releases/rc-[version]` should be crated.
In that branch the version should be bumped to \[version\]-rc.
After the release candidate have been finalized (i.e. update documentation, all version references, run integration tests), the version must be bumped to delete the *-rc*.
Then, it should me merged with the *master*, and a tag must be created for the version.
Creating and pushing the tag will signal de CI to release the stable version.

# Testing

Test classes must be tagged, via `@Tag` annotations, as either *unit* or *integration* tests.
This tags are used by CI to run different sets of tests for different branches.

# Continuous Integration

We use CircleCi for continuous integration.
The following configuration is in place:

- All branches run unit tests. No sources or javadoc are built
- Develop branch runs unit tests. Sources are built. Artifacts are deployed.
- Release candidate branch runs unit tests and integration tests. Sources and javadocs are built.
- Master branch does not run anything
- Tags build sources and javadocs, and deploy  

