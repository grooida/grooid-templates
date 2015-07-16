import static org.apache.commons.io.FilenameUtils.concat
import static org.apache.commons.io.FileUtils.moveFile

// --------------------------------------------
// --------------- DEFAULTS -------------------
// --------------------------------------------

def props = [projectName: projectDir.name]

// --------------------------------------------
// --------------- QUESTIONS ------------------
// --------------------------------------------

props.defaultPackage    = ask('DEFAULT source code package ? [grooid.app]: ', 'grooid.app', 'defaultPackage')
props.minSdkVersion     = ask('MIN version of SDK you want to target ? [19]: ', '19', 'minSdkVersion')
props.targetSdkVersion  = ask('MAX version of SDK you want to target ? [21]: ', '21', 'targetSdkVersion')
props.buildToolsVersion = ask('DEFAULT version for Android Build Tools ? [22.0.1]: ', '22.0.1', 'buildToolsVersion')
props.androidSupportV4  = ask('DEFAULT version for Android support v4 ? [21.0.0]: ', '21.0.0', 'androidSupportV4')

// --------------------------------------------
// ----------- PROCESSING TEMPLATES -----------
// --------------------------------------------

processTemplates 'README.md', props
processTemplates 'build.gradle', props
processTemplates '**/strings.xml', props
processTemplates '**/AndroidManifest.xml', props
processTemplates '**/layout/**.xml',props

// -------------------------------------------------
// -------- PROCESSING GROOVY MAIN TEMPLATES -------
// -------------------------------------------------

def defaultBaseCodePath         = new File(projectDir, 'code')
def groovyCodePackagePath       = props.defaultPackage.replace('.' as char, '/' as char)

def groovyCodeTemplatesPath     = new File(defaultBaseCodePath, 'main')
def groovyCodeBasePath          = 'src/main/groovy'
def groovyCodeDestinationPath   = new File(projectDir, concat(groovyCodeBasePath, groovyCodePackagePath))

processCode(groovyCodeTemplatesPath, groovyCodeDestinationPath, props)

// -------------------------------------------------
// -------- PROCESSING GROOVY TEST TEMPLATES -------
// -------------------------------------------------

def groovyCodeTestTemplatesPath     = new File(defaultBaseCodePath, 'test')
def groovyCodeTestBasePath          = 'src/androidTest/groovy'
def groovyCodeTestDestinationPath   = new File(projectDir, concat(groovyCodeTestBasePath, groovyCodePackagePath))

processCode(groovyCodeTestTemplatesPath, groovyCodeTestDestinationPath, props)

// delete 'code' directory
defaultBaseCodePath.deleteOnExit()

/**
 * Process source file templates at fromDir and moves them at
 * toDir. Then deletes source file templates.
 *
 * @param fromDir where the templates are located
 * @param toDir final destination
 **/
void processCode(File fromDir, File toDir, Map projectProperties) {
    fromDir.listFiles().each { File file ->
        // Processing each template
        processTemplates "**/${file.name}", projectProperties

        // Moving groovy file to the right place
        def sourceName = file.name.replace('gtpl','groovy')
        def destination = new File(toDir, sourceName)

        moveFile(file, destination)
    }

    fromDir.delete()
}