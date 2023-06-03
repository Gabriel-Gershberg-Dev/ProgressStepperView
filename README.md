### Summary  
Stepper, helps you to show beautiful step animation especially registration steps or some process which contains lots of steps. In order to notify to user and show the current situation and prevent them to be bored.

### Download

This library is available in **jitpack**, so you need to add this repository to your root build.gradle at the end of repositories:
   
```groovy  
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency:

```groovy 
dependencies {
  	   implementation 'com.github.Gabriel-Gershberg-Dev:ProgressStepperView:Tag'

}
``` 

## Sample Usage  

<img height="500" align="right" src="https://github.com/Gabriel-Gershberg-Dev/ProgressStepperView/assets/64218293/fd17f30f-6079-4c1b-908d-f2eac84f2767"></img>

### In your Activity/Fragment
#### To go forward
```
stepper.forward()
```
#### To go back
```
stepper.back()
```
#### To progress
```
stepper.progress(loopsize = 3) // Default value is 0
```




#### Bonus(Complete Listener)
```
stepper.progress(loopsize = 3).addOnCompleteListener {
    // Here some magic stuffs
}
```
