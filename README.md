# PIN Manager

PIN Manager is a sample app for managing PIN codes in apartments.

App uses:
* [Jetpack Compose](https://developer.android.com/compose) to build UI.
* [Room database](https://developer.android.com/training/data-storage/room) to store PIN objects locally.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) to manage dependency injection.
* MVVM architecture with domain, data and ui layer.

## Screenshots
<p>
  <img src="https://github.com/kubamyka/pin-manager/blob/master/screenshots/pinList.jpg" width="200" title="PIN list">
  <img src="https://github.com/kubamyka/pin-manager/blob/master/screenshots/deletePin.jpg" width="200" title="Delete PIN">
  <img src="https://github.com/kubamyka/pin-manager/blob/master/screenshots/addPin.jpg" width="200" title="Add PIN">
  <img src="https://github.com/kubamyka/pin-manager/blob/master/screenshots/replacePin.jpg" width="200" title="Replace PIN">
</p>

## Features

### Main PIN list
Package: [com.kmcoding.pinmanager.ui.home](https://github.com/kubamyka/pin-manager/tree/master/app/src/main/java/com/kmcoding/pinmanager/ui/home)

This screen shows the list of PIN objects fetched from local Room database. Each PIN object displays lock icon, name, code and delete button.

### PIN object creator
Package: [com.kmcoding.pinmanager.ui.add](https://github.com/kubamyka/pin-manager/tree/master/app/src/main/java/com/kmcoding/pinmanager/ui/add)

This screen allows user to create new PIN object by entering its name. If entered name is already being used by another object, then user has to decide if he wants to replace old PIN object with new one. System generates 6 digits unique code which is applied to newly created PIN object which is being stored in local Room database.

## Tests

### Instrumented tests
Class: [PinDaoTest.kt](https://github.com/kubamyka/pin-manager/blob/master/app/src/androidTest/java/com/kmcoding/pinmanager/PinDaoTest.kt)

This instrumented tests were written to test database operations through PIN data access object.

### Unit tests
Class: [HomeViewModelTest.kt](https://github.com/kubamyka/pin-manager/blob/master/app/src/test/java/com/kmcoding/pinmanager/HomeViewModelTest.kt)

This unit tests were written to test methods and operations on PIN list view model.

Class: [AddNewPinViewModelTest.kt](https://github.com/kubamyka/pin-manager/blob/master/app/src/test/java/com/kmcoding/pinmanager/AddNewPinViewModelTest.kt)

This unit tests were written to test methods and operations on PIN object creator view model.

## License
### Icons
<a href="https://www.flaticon.com/free-icons/login" title="login icons">Login icons created by Freepik - Flaticon</a>
