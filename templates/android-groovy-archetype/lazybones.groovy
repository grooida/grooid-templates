import static org.apache.commons.io.FilenameUtils.concat
import org.apache.commons.io.FileUtils

def props = [:]

// --------------------------------------------
// --------------- QUESTIONS ------------------
// --------------------------------------------

props.projectName = projectDir.name

props.defaultPackage =
    ask("Define value for default package [org.company.example]: ",
    "org.company.example",
    "defaultPackage")
props.minSdkVersion =
    ask("Which is the minimum version of sdk you want to target ? [19]: ",
    "19",
    "minSdkVersion")
props.targetSdkVersion =
    ask("Which is the main version of SDK you are targeting ? [21]: ",
    "21",
    "targetSdkVersion")
props.buildToolsVersion =
    ask("Which version of Android Build Tools do you want to use ? [20.0.0]: ",
    "20.0.0",
    "buildToolsVersion")

// --------------------------------------------
// ----------- PROCESSING TEMPLATES -----------
// --------------------------------------------

processTemplates "README.md", props
processTemplates "build.gradle", props
processTemplates "**/strings.xml", props
processTemplates "**/MainActivity.gtpl", props
processTemplates "**/AndroidManifest.xml", props

def sourcePath = "src/main/groovy"
def packagePath = props.defaultPackage.replace('.' as char, '/' as char)
def sourceDestination =
    new File(
        projectDir,
        concat(concat(sourcePath, packagePath), "MainActivity.groovy")
    )

sourceDestination.parentFile.mkdirs()

// --------------------------------------------
// --------- MOVE FILES TO DESTINATION --------
// --------------------------------------------

FileUtils.moveFile(new File(templateDir, "MainActivity.gtpl"), sourceDestination)
