# Fish-Market-Place-Buyer
The app is built using Kotlin while the backend is powered by AWS. The app also uses the Amplify Framework from AWS. Before running this project you will need to set up an AWS account and also set up Amplify inside the project.

To Setup Amplify : 

1. Install amplify in your dev environment
```
npm install -g @aws-amplify/cli
```

2. Setup amplify by linking the project with your newly created AWS accounty
```
amplify configure
```

3. Initialize Amplify 
```
amplify init
```

4. Add amplify build gradle plugin to the root gradle file

```
buildscript {
   repositories {
       google()
       jcenter()
   }

   dependencies {
       classpath 'com.android.tools.build:gradle:4.1.2'

       // Add this line into `dependencies` in `buildscript`
       classpath 'com.amplifyframework:amplify-tools-gradle-plugin:1.0.2'
   }
}
allprojects {
   repositories {
       google()
       jcenter()
   }
}
// Add this line at the end of the file
apply plugin: 'com.amplifyframework.amplifytools'
```

5. Add the project dependencies to your app module's gradle files.
```
dependencies {
    implementation 'com.amplifyframework:aws-api:1.18.0'
    implementation 'com.amplifyframework:aws-datastore:1.18.0'
}
```

For more information on setting up amplify please checkout the amplify docs
https://docs.amplify.aws/start/getting-started/installation/q/integration/android


6. Choose the required set up of features from Amplify as necessary. 


