# 1. Solar Village
Solar Village is a home work assignment for Advanced Process Development with Red Hat Process Automation Manager. It contains following modules:

1.  **New Orders**: This is the Business Application(kjar) containing the "New Order Permitting" business process. This process calls Govt.PermitService in every 2 minutes to track the permit approval status, this tracking cycle should be set longer for real time application.

2.  **Govt.PermitService**:  Its a Government REST service simulator project. It provides RESTful CRUD APIs for permit applications. It uses a MySQL database to store permit application data. To simulate real life scenario, the permit status needs to be updated in the database. 
Permit approval results (APPROVED/DENIED/INPROGRESS) need to be provided as mock results when the permit application data is submitted at the first time.

3. **CustomWorkItemHandlers**: Contains the custom work items to interact with the Govt.PermitService and uses a Java Rest Client i.e. implemented in DomainModel to communicate.
4.  **DomainModel**: This contains the POJOs and hibernate controllers used by all the applications NewOrders, Govt.PermitService and CustomWorkItemHandlers.

Please follow following instructions to set up the environment and run this demo.


# 2. Standalone/Virtual Machine environment

This POC uses the Standalone installation of the PAM Central and Kie Server in Windows operating system.

The files required for the standalone installation can be obtained from the **bxms-advanced-development71_2.vdi** virtual machine from the advanced course.

You can download a copy of the VM from:
https://drive.google.com/file/d/1qUf36dL9yaPiJu2ofNg7sJqvAtfoTd0O/view.

# 3. Instructions
## 3.1. Build and deploy the maven modules
### 3.1.1. Clone the repository to your local environment.

It is required to clone the remote maven repository in order to build and deploy the jars and wars that will serve the government permit services:

* Add electrical/structural permit requests
* Rescind electrical/structural permit requests
* Query the government permit requests status

1. Create a folder to organize your git repositories.

  ```
  > mkdir C:/Users/<username>/gits
  > cd C:/Users/<username>/gits
  ```

2. Execute the git clone command:  

  ```
  > git clone url
  ```

### 3.1.2. Build the Maven components

The war and jar components that will serve the government permit REST web services are built during this step. We are using Maven, and as an standard practice, those components will be generated in their respective `target` folders.

Using the git repository that we cloned in the previous step, execute the maven install command:

```
> cd SolarVillagePOC
> mvn install -P war
```

> **Note**: the war profile is configured to generate a war component for the service tier project.

### 3.1.3. Deploy the SOAP Web Server components

The war and jar components that provide the REST web service functionality are `maven-webapp` components that can be deployed to any java based web server environment.

Copy the Government Permit war file to JBoss Server:

```
> robocopy "sourcepath" "{$EAP_HOME}\standalone\deployments" filename.war
```

> **Note:** the POM for OrderPermit project includes the dependency instruction for governmentPermitServices parent project.

## 3.2. Build and deploy the business assets in Kie Server
### 3.2.1. JBoss EAP environment preparation

In this section we will deploy the _New Orders_ project to our _Kie Server_.

The  _**Kie Server**_  is a modular, standalone server component that can be used to instantiate and execute rules and processes. It exposes this functionality via REST, JMS and Java interfaces to client application. It also provides seamless integration with the  _PAM Central_.

The _Kie Server_ is a web application that can be deployed in _JBoss EAP_, _Wildfly_, _Tomcat_ or any other Java application server or web container. It works by accessing kjars from a Maven repository and exposing its rules and processes throught HTTP or JMS.

When a _Kie Server_ uses the _Business-Central_ as its _Controller_, it happens to be a _**Managed Kie Server**_. We will be using this type of configuration for our server.
