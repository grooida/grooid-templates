
import static org.apache.commons.io.FilenameUtils.concat

import org.apache.commons.io.FileUtils

def props = [:]

props.projectName = projectDir.name
props.defaultPackage =
    ask("Define value for default package [org.company.example]: ",
        "org.company.example",
        "defaultPackage")

processTemplates "README.md", props
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

FileUtils.moveFile(new File(templateDir, "MainActivity.gtpl"), sourceDestination)
