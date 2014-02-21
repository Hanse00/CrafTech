CrafTech
========

## Setting up the workspace
To set up the ForgeGradle environment consists for a few fairly simple steps.

1. Download the files in the repository to your local system.
2. Run the gradle script.
  * On Windows: `gradlew.bat setupDevWorkspace eclipse`
  * On UNIX based systems: `gradlew setupDevWorkspace eclipse`
3. Open the `eclipse` folder as your workspace.
  * Example: Your project is located at `C:\Projects\CrafTech`, open `C:\Projects\CrafTech\eclipse` as your workspace.
4. **Code!**

To build the mod is even simpler. If you have not yet done the above steps, complete them first.
After the workspace has been properly built, simply run the following command.

* On Windows: `gradlew.bat build`
* On UNIX based systems: `gradlew build`

The built jar file should now be located at `<project directory>\build\libs\<mod name>-<mod version>.jar`

To set the mod name and mod version variables used in the generation of the jar file, edit the following default values in `build.gradle`

* version = "1.0"
* group = ""com.yourname.modid"
* archivesBaseName = "modid"
