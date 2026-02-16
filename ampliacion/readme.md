Al arrancar la app, se muestra una pantalla para seleccionar la categoría de recetas que se quiere consultar. 
La selección se hará con Radiobutton, como se muestra en la figura. 

La primera vez que se instala la aplicación, no hay ningún elemento seleccionado. 

Las categorías son: 

Ternera, Pollo y Postre

Al pulsar alguna de las categorías pasará a la siguiente pantalla. 

<img width="256" height="556" alt="image" src="https://github.com/user-attachments/assets/4b316b08-d547-4564-9493-ed810d885989" />

En la siguiente pantalla, se muestran las recetas filtradas por la categoría seleccionada. 
(en la figura se ha seleccionado Ternera)

<img width="272" height="551" alt="image" src="https://github.com/user-attachments/assets/5aed3999-a8a1-4320-95ac-bb04c628389a" />



En el ejemplo resuelto se ha usado la colección de datos facilitada en recursos, si decides crear la tuya propia, debes tener en cuenta que las imágenes de las recetas deben estar alojadas en internet, no como recursos locales de la aplicación. 

Además la aplicación debe tener, como se muestra en la figura, una barra de herramientas, con el botón de navegación a la pantalla anterior, y un botón   para salir completamente de la app (no a la pantalla anterior).  La imagen del botón se incluye en los recursos del aula virtual en formato SVG para su importación al proyecto.

Si se gira el dispositivo, deberá mostrarse  la pantalla en el siguiente formato.

<img width="478" height="237" alt="image" src="https://github.com/user-attachments/assets/352c65d3-93dc-41dd-a088-97afa69569e2" />

Si se cambia el idioma del teléfono a inglés, la aplicación mostrará el título en inglés (no  las categorías ni el contenido de las recetas), fíjate en las imágenes.
<img width="189" height="416" alt="image" src="https://github.com/user-attachments/assets/7bb8e876-f9c5-493c-9c5d-0332641571b9" />


En la primera pantalla se cambia solamente el título, y en la segunda en la barra de herramientas, también el título. 

<img width="186" height="426" alt="image" src="https://github.com/user-attachments/assets/e5071d50-7bfd-4f07-b1f8-0d5f5b18c37e" />


Al pulsar sobre cualquier elemento de la lista (cualquier receta) éste se borrará, y aparecerá un mensaje en la pantalla de “Receta eliminada”. 

Mientras la aplicación no se cierre, se mostrarán las listas sin los elementos que se hayan podido eliminar. 

No es necesario realizar persistencia, solamente se mantienen los cambios en memoria, mientras la app está abierta. 

(En la imagen se han borrado dos recetas, y se muestra el mensaje en el último borrado).

Fíjate que no es necesario traducir los mensajes emergentes. 

<img width="236" height="505" alt="image" src="https://github.com/user-attachments/assets/3d022212-a005-4efd-b8ef-f535029e5554" />



Una vez que se ha seleccionado una categoría, si se cierra completamente la aplicación, al volver a entrar, aparecerá directamente la última categoría que se hubiera seleccionado.

Y un mensaje de “Actualizadas tus preferencias”. 

Éste mensaje solo aparece cuando llegamos a esta pantalla cuando se entra directamente.

Se muestra en el ejemplo la pantalla de recetas de pollo, que se supone era la última categoría seleccionada. 

<img width="234" height="506" alt="image" src="https://github.com/user-attachments/assets/ee12fcd8-57fc-4bb8-88e8-0a0e5f688849" />







