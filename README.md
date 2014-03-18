#CrafTech

CrafTech is a Minecraft mod aiming to implement large changes to the crafting system (Specifically ore processing for the time being), with the goal of improving the technological aspect of the game.

**Latest release:** [CrafTech 0.1.1 for Minecraft 1.7.2](https://github.com/Hanse00/CrafTech/releases/tag/v0.1.1)

* [Downloads](https://github.com/Hanse00/CrafTech/blob/master/README.md#downloads)
* [License](https://github.com/Hanse00/CrafTech/blob/master/README.md#license)
* [Contributing](https://github.com/Hanse00/CrafTech/blob/master/README.md#contributing)
  * [Localization](https://github.com/Hanse00/CrafTech/blob/master/README.md#localization)
  * [Pull Requests](https://github.com/Hanse00/CrafTech/blob/master/README.md#pull-requests)
  * [Issues](https://github.com/Hanse00/CrafTech/blob/master/README.md#issues)
* [Working with the code](https://github.com/Hanse00/CrafTech/blob/master/README.md#working-with-the-code)
  * [Compiling](https://github.com/Hanse00/CrafTech/blob/master/README.md#compiling)
  * [Setting up the workspace](https://github.com/Hanse00/CrafTech/blob/master/README.md#setting-up-the-workspace)

## Downloads

***

All the mod downloads can be found on the [release page](https://github.com/Hanse00/CrafTech/releases).

## License

***

<img src="https://www.gnu.org/graphics/lgplv3-147x51.png" />

CrafTech is released under the LGPLv3 license, in layman's terms this means:
* You can distribute this mod as you like. (That's a go on modpacks!)
* You can take a look at all the source code, modify it, copy it, whatever, as long as your code based on CrafTech is also licensed under LGPLv3.
* I'm not promising you that the code in CrafTech will work in any intentional way what so ever. (I'll do my best not to crash your client and corupt your worlds though, scout's honor!)

Needless to say I'm no lawyer, if you truely want to understand the rights granted to you by the LGPL license, you should really read it.  
No, really.

## Contributing

***

### Localization

If you wish to port CrafTech to your language of choice, look no further!

1. Follow the steps for making a pull request.
2. In step 3, copy the `en_US.lang` file, and rename it to your language based on the [IETF language tag](https://en.wikipedia.org/wiki/IETF_language_tag). (Eg. `da_DK.lang` or `fr_FR.lang`)
3. Make a pull request!

### Pull Requests

Found an issue you're confident that you can fix? Awesome!  
To submit changes to CrafTech's code, you'll have to fork the project, do your changes, and submit a pull request.

1. Click the `Fork` button at the top right of this page.
2. Using your forked repository, follow the instructions to [setup your workspace](https://github.com/Hanse00/CrafTech/blob/master/README.md#setting-up-the-workspace).
3. Use git locally to commit your changes, give commit names relevant to what you're doing.
4. Click the `Pull Request` button to compare your changes to the CrafTech master files.
5. Give it a fitting title and explain what you've done.
6. Await... [Insert spooky music]

### Issues

If you've found a bug, have feature suggestions, or your game is crashing with CrafTech installed, here's what you do!

1. Make sure your issue is valid - Hasn't been reported before, isn't an issue with some other mod or similar.
2. Open the [issues tab](https://github.com/Hanse00/CrafTech/issues).
3. Create a new issue by clicking `New Issue` in the top right.
4. Write a good sumarizing title, and as detailed a description of your idea or bug, as possible (Images are welcome too).

## Working with the code

***

### Compiling

If you for some reason feel inclined to compile the mod on your own, based on the latest source, the following steps should get you through the process:

1. Clone the repository to your computer.
2. Inside the CrafTech folder, execute `gradle setupDevWorkspace`.
  * This step is not needed if you've followed the instructions for working on the mod.
3. Execute `gradle build`.
4. Navigate to `CrafTech/build/libs`.
  * This folder should contain a file `CrafTech-[mc version]-[craftech version]-DEV.jar`
5. Copy the generated file into your minecraft mods folder - as usual.

### Setting up the workspace

If you'd like to play around with CrafTech's code, you'll likely want to set it up in an IDE of your choice.
Instructions for getting set up in IDEA and Eclipse can be found below.

1. Download the files in the repository to your local system.  
  If you intend on using **Eclipse**:  
  2. Download the apropriate source version of forge (Version can be found by opening `build.gradle` and looking at the Minecraft vesion number which will be formatted as `<minecraft>-<forge>`).
  3. Copy the `eclipse` folder from the forge src, into the CrafTech project.
2. Run the gradle script `gradle setupDecompWorkspace <eclipse / idea>`.
  * If this gives you issues, try running `gradle cleanCache setupDecompWorkspace <eclipse / idea> --refresh-dependencies`.
3. Open the generated project.  
  With **Eclipse**:
  * Open the eclipse sub-folder of the project, as your workspace.
  * Example: Your project is located at `C:\Projects\CrafTech`, open `C:\Projects\CrafTech\eclipse` as your workspace.

  With **IDEA**:
  * Select `Open Project` in the IDEA menu.
  * Navigate to the main folder of your project, IDEA should recognize this as a project (Otherwise select the `CrafTech.ipr` file specifically).
  * Optional: Run `gradle genIntelliJRuns`, this will generate Client and Server run configurations.

6. **Code!**

**Notice**: This is just one of many ways to set up ForgeGradle, feel free to try anything else you want to, I'm by no means an expert in the system.
