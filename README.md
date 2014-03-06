CrafTech
========

A mod which adds technologically true elements to Minecraft.


## Setting up the workspace
To set up the ForgeGradle environment consists for a few fairly simple steps.

1. Download the files in the repository to your local system.  
  If you intend on using **Eclipse**:  
  2. Download the apropriate source version of forge (Version can be found by opening `build.gradle` and looking at the Minecraft vesion number which will be formatted as `<minecraft>-<forge>`).
  3. Copy the `eclipse` folder from the forge src, into the CrafTech project.
2. Run the gradle script.
  * On **Windows**: `gradlew.bat setupDecompWorkspace <eclipse / idea>`
  * On **UNIX** based systems: `gradlew setupDecompWorkspace <eclipse / idea>`
3. Open the generated project.  
  With **Eclipse**:
  * Open the eclipse sub-folder of the project, as your workspace.
  * Example: Your project is located at `C:\Projects\CrafTech`, open `C:\Projects\CrafTech\eclipse` as your workspace.
  
  With **IDEA**:
  * Select `Open Project` in the IDEA menu.
  * Navigate to the main folder of your project, IDEA should recognize this as a project (Otherwise select the `CrafTech.ipr` file specifically).
  * Optional: Run `gradlew(.bat) genIntelliJRuns`, this will generate Client and Server run configurations.

6. **Code!**

**Notice**: This is just one of many ways to set up ForgeGradle, feel free to try anything else you want to, we're by no means experts in the system.

## Building the mod
To build the mod is even simpler. If you have not yet done the above steps, complete them first.
After the workspace has been properly built, simply run the following command.

* On Windows: `gradlew.bat build`
* On UNIX based systems: `gradlew build`

The built jar file should now be located at `<project directory>\build\libs\<mod name>-<mod version>.jar`

To set the mod name and mod version variables used in the generation of the jar file, edit the following default values in `build.gradle`

```
version = "1.0"
group = ""com.yourname.modid"
archivesBaseName = "modid"
```
