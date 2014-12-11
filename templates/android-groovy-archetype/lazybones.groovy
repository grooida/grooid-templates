import static org.apache.commons.io.FilenameUtils.concat
import static org.apache.commons.io.FileUtils.moveFile

def props = [:]

// --------------------------------------------
// --------------- QUESTIONS ------------------
// --------------------------------------------

props.projectName = projectDir.name

props.defaultPackage =
    ask('Define value for default package [grooid.app]: ',
    'grooid.app',
    'defaultPackage')
props.minSdkVersion =
    ask('Which is the minimum version of sdk you want to target ? [19]: ',
    '19',
    'minSdkVersion')
props.targetSdkVersion =
    ask('Which is the main version of SDK you are targeting ? [21]: ',
    '21',
    'targetSdkVersion')
props.buildToolsVersion =
    ask('Which version of Android Build Tools do you want to use ? [20.0.0]: ',
    '20.0.0',
    'buildToolsVersion')

// --------------------------------------------
// ----------- PROCESSING TEMPLATES -----------
// --------------------------------------------

processTemplates 'README.md', props
processTemplates 'build.gradle', props
processTemplates '**/strings.xml', props
processTemplates '**/AndroidManifest.xml', props

// --------------------------------------------
// -------- PROCESSING GROOVY TEMPLATES -------
// --------------------------------------------

def groovyCodeTemplatesPath = new File(projectDir, 'code')

def groovyCodeBasePath = 'src/main/groovy'
def groovyCodePackagePath = props.defaultPackage.replace('.' as char, '/' as char)
def groovyCodeDestinationPath =
    new File(
        projectDir,
        concat(groovyCodeBasePath, groovyCodePackagePath))

// Looping over all groovy code templates
groovyCodeTemplatesPath
   .listFiles()
   .each { File file ->

       // Processing each template
       processTemplates "**/${file.name}", props

       // Moving groovy file to the right place
       def sourceName = file.name.replace('gtpl','groovy')
       def destination = new File(groovyCodeDestinationPath, sourceName)

       moveFile(file, destination)
   }

// --------------------------------------------
// -------- PROCESSING ANDROID LAYOUTS  -------
// --------------------------------------------

def layoutsTemplatesPath = new File(projectDir, 'layouts')
def layoutsBasePath = new File(projectDir,'src/main/res/layout/')

layoutsTemplatesPath
    .listFiles()
    .each { File source ->

        // Processing each template
       processTemplates "**/${source.name}", props

       // Moving groovy file to the right place
       def destination = new File(layoutsBasePath, source.name)

       moveFile(source, destination)

     }

// --------------------------------------------
// --------------- CLEANING UP ----------------
// --------------------------------------------

groovyCodeTemplatesPath.delete()
layoutsTemplatesPath.delete()
