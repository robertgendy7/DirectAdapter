# DirectAdapter
RecyclerView adapter without the need to implement classes
just pass in the viewbinding of the adapter layout and the count of items held by the adapter. <br /> <br />

![alt text](https://github.com/robertgendy7/DirectAdapter/blob/master/Screen%20Shot%202021-04-07%20at%204.48.31%20PM.png)

## How it works 
the library uses reflection and view binding , it uses reflection to call the inflation methods
on the viewbinding instance and then uses callbacks according to the cycle of the RecyclerView.Adapter 
class , the base class for all the adapter is the ```DirectAdapter``` class which is a recyler view adapter 
of ```GenericViewHolder```.

### Adapter for a single layout
![alt text](https://github.com/robertgendy7/DirectAdapter/blob/master/Screen%20Shot%202021-04-07%20at%204.54.38%20PM.png)


### Mutli-layout adapter
![alt text](https://github.com/robertgendy7/DirectAdapter/blob/master/Screen%20Shot%202021-04-07%20at%204.54.54%20PM.png)

## How to use
download the code and then import the DirectAdapter module to your project and add it to the dependancies 
of your desired module in the project structure.

### pros: 
- faster coding
- activity code is a bit linear so you dont have to dance around classes in development

### cons :
- slower preformance than that of the standard way.

##### contact me 
###### linkedin : https://www.linkedin.com/in/robert-maurice-a43b0b1b1/ <br />
###### email : robertgendy7@gmail.com <br />
###### another email : robertmaurice15@gmail.com <br />





