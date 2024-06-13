# UniverSocial

_Esta aplicación fue pensada y creada para presentar como proyecto final en un ciclo de Desarrollo de Aplicaciones Multiplataforma. Es por esto mismo que se ha hecho con los lenguajes, tecnologías y herramientas requeridas en el propio centro._

Universocial es una aplicación móvil que tiene como objetivo conectar a los entusiastas de la astronomía, brindándoles una experiencia enriquecedora y social. La plataforma ofrece información sobre eventos celestes y permite a los usuario crear perfiles, buscar eventos locales y conectarse con los demás aficionados para preguntar sus dudas o realizar quedadas mediante un chat. Esta aplicación pretende transformar la observación del cielo en una actividad comunitaria. 

![image](https://github.com/EurekaZC/UniverSocial/assets/128409004/368b4a1c-2087-44dd-8f6d-c97071bf4afd)



## Comenzando 🚀

Descarga el proyecto completo desde este repositorio, donde se encuentra todo el código necesario para su despliegue. 

> Mira **Despliegue** para conocer como desplegar el proyecto.



### Lenguajes y herramientas usadas

+ Java
+ SQL
+ Android Studio



### Pre-requisitos 📋

Tener a disposición Android Studio, VirtualBox u algún entorno donde ejecutar un proyecto Java.

> Mira **Construido con** para información más detallada.



## Despliegue 📦

PAra ejecutar la aplicación y obtener un funcionamiento completo, se deben seguir los siguientes pasos:

1. Abrir una máquina virtual con Windows 7 donde tendremos NetBeans 8.1 y SQLDeveloper 4.1.3 instalados.
   
2. Crearemos una conexión para la base de datos con un usuario en el SQLDeveloper.
   
  ![image](https://github.com/EurekaZC/UniverSocial/assets/128409004/59c33f66-c64c-430f-bb62-d9ab779cb70a)

  
3. Cargaremos los scripts de creación de la base de datos e inserción de los datos de prueba.

 ![image](https://github.com/EurekaZC/UniverSocial/assets/128409004/bac5d7d6-d8eb-48b4-ab08-be30312cac3b)

4. Ahora abriremos el proyecto `AstronomiaSC` y ejecutaremos su main para que el hilo ejerza el papel de servidor.

   ![image](https://github.com/EurekaZC/UniverSocial/assets/128409004/f4262a1c-ea98-4266-9435-b0d3e32faa9d)

5. Por último, deberemos abrir Android Studio versión Giraffe con SDK 33 en nuestra máquina física (ejerce el papel de cliente), cambiar la ip a la de tu ip-servidor y ejecutar el emulador. Ya puedes disfrutar de la aplicación.

   ![image](https://github.com/EurekaZC/UniverSocial/assets/128409004/d87f4604-c9fd-4f30-9db9-b1fba39c4abb)




## Construido con 🛠️

Para llevar a cabo el proyecto y desplegarlo, se han usado las siguientes herramientas y teconologías.

**Parte del cliente**

+ Máquina física con Windows 11
+ NetBeans versaión 8.1 
+ Android Studio versión Giraffe con emulador Android Pixel a4 API 34 y SDK 33 

**Parte servidor**

+ Virtualbox con máquina Windows 7
+ NetBeans versión 8.1
+ SQLDeveloper versión 4.1.3



## Nota 📖

Se hará a futuro un remake del proyecto con nuevas tecnologías y herramientas. Se tiene pensado usar Spring Boot, protocolo HTTP y monitorizar su uso, así como crear un Docker que ejerza el papel de servidor. Además, se pretende cambiar el lenguage usado en Android de Java a Kotlin.



## Autores ✒️

+ **Cristina Zas Pérez**



