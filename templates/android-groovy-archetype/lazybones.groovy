
def props = [:]

props.projectName = projectDir.name

processTemplates "README.md", props
processTemplates "**/strings.xml", props

