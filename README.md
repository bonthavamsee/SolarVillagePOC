

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

Detailed instructions on how to use this project are given in this PDF document.
[SolarVillage POC Documentation](src/resources/SolarVillage_Documentation.pdf)