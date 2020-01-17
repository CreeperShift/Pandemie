![](/assets/logo.png)

###### by Max Henkes

---

### 1. Introduction

This repository contains our submission for [InformatiCup2020](https://github.com/informatiCup/informatiCup2020). Protocol 1 attempts to stop the spreading of pathogens in a semi-realistic, but simplified, model of the world. It introduces pathogens in a round-based webservice, where it makes decision based on prior analysis, which will ultimately save or destroy the world.

---





### 2. Content

> I want to build this project from source.

Check out the paragraph titled "Building from source"

> I want to run this project in a docker container.

Check out the paragraph titled "Running the docker container"

> I want to read the full documentation.

You have 2 options here. Either head over to our github page, [Protocol 1 Page](https://creepershift.github.io/Pandemie/), to read it all online, or download the latest Documentation file, "Documentation.pdf" in the /docs folder of the repository.

---





### 3. Building from Source

Building from source is rather easy thanks to [Gradle Build Tool](https://gradle.org/). All that is required is a JDK version of Java 11 installed and a proper JAVA_HOME entry in your os. The Gradle binary is distributed with the source code.

1. Simple download the repository to your computer

2. Inside the base folder, open your CMD of choice and run

3. ```powershell
   ./gradlew build
   ```

4. 
   The built jar file can now be found at /build/libs/pandemie-version.jar

5. Check out the paragraph "Running from source file"

---





### 4. Running from source file

This step assumes you have a working setup of Java 11 installed on your computer and have built the jar file according to step 3.

You can now run the program with default cmd arguments using

```powershell
java -jar /build/libs/pandemie-version.jar
```

If you want to run the jar with custom arguments, take a look at this table:

| Arg         | alt. Arg             | Changes                                                                          |
|:-----------:| -------------------- | -------------------------------------------------------------------------------- |
| -e <number> | --port <number>      | Changes the port from default 50123                                              |
| -l          | --logging            | Enables logging of various events                                                |
| -v          | --visual             | Enables visualisation (does not work inside docker or on a server with cmd only) |
| -t <number> | --sleepTime <number> | Time to sleep between rounds. Can be used to better visualize changes.           |

---



### License

Logo made using icons by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
