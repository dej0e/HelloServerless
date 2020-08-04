mkdir -p expanded
unzip /Users/djo/IdeaProjects/helloLambda/build/distributions/helloLambda-1.0-SNAPSHOT.zip -d expanded
find ./expanded/lib -name '*.jar' | xargs -n1 zipinfo -1 | grep '.*.class' | sort | uniq -c | sort