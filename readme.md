# Zxiba CommandLine Parser beta-1.0

## About
---
Zxiba stands for "奇耙"(People with Strange Way of thinking) in Chinese.
##
Zxiba is a command line parser inspired by actual need to create applications in short time. It aims to provide a super-easy-to-learn command line parser for Java developer.
##
Zxiba flattens commandline parsing and gets rid off the need for annotation or defining structure before using parsing.(Though the features are planned for future releases, but they are just some extra features.) 
 ##
 Zxiba can be used out-of-the-box, with almost no-learning or preparation required, and has a Single end-point for parsing commandline, you don't have to deal with classes like "Option","arguments" or "parameters" yourself.

 ## version naming
 Zxiba uses old-school naming system. The version number of Zxiba is consisted of
    
     [version_type][spec_version].[release_number]

<br>version_type :beta or empty, if empty, it is an official release. 
<br>spec_version : always integer, means the spec this release is follow.
<br>release_number : named after how many months have passed since after the release of this spec, multiple release within a month are separated by adding alphabet(a-zzz) behind it.

### Example
    beta1.15 // 15th beta release for spec version 1 
    1.0 // official version1
    1.25 // official release for spec version 1 , released 25 months after initial release of spec version 1.
    1.25aa // the 26th release for spec version 1 in the month 25 months after initial release.    


## Key Feature
---
### No need to Define anything prior
Zxiba , unlike many popular commandline parsing library , such as jCommander,args4j. Which requires user to define command line argument structure through annotation or object structure. All you need to start using Zxiba is the arguments you received from commandline.

```JAVA
    pubilc static void main(String[] args){
        //throw in the arguments and create a ZxibaParser
        ZxibaParser zxParser= new ZxibaParser(args);
    }
```
▲
This is ALL you need to get start.
##
Too simple ? This is what Zxiba is intended.<br> 

## Flattened
The only thing you need to deal with while parsing commandline arguments is ZxibaParser, nothing else.
ZxibaParser has everything you need written into many functions.(May seemed redundant, but that simplifies structure)

## Flow at your control
Zxiba gives developer complete control to program execution flow. Things either happen in front of you or not happen at all.

## Getting Zxiba 
---
Zxiba is currently on beta. You can find beta releases on Githu page. It should goto maven central as soon as beta ends.

## Future development for Zxiba
---
- ZxibaRunner
<br>A helper for handling process-flow , pre-requirement and other.
Here's what it might look like :
    ```JAVA
    public class RunnerExample{
        public static void main(String[] args){
            RunnerExample re= new RunnerExample();
            ZxibaRunner zxRunner=new ZxibaRunner(args);
            zxRunner.runLater(
                new ZxibaRunnable<RunnerExample>(zxRunner,re){
                void run(){re.function1();}
                }
            )
            .hasKey("key1")
            .hasKeyWithParam("key2","key3")
            .hasOptionWithParam("key1","opt1","op1a","op2a");
            zxRunner.runLater(
                ...
            )
            zxRunner.run();
        }
    }

    ```
    In the above example, program are not "run" until ZxibaRunner.run is called. This prevents any program from running before all checks are done and required parameters are present.
<br>
<br>
- Annotation and structure definition

<br>This may seemed contradict, but these are indeed something to help developer. Especially annotation. As I said before, these are extra features, not center for Zxiba.

- Multi-type validations
    <br> Ability to check if parameter are valid, can be highly customized.