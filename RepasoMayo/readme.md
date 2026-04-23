El objetivo de la práctica es trabajar los resultados de aprendizaje de la parte Android.

Layouts, intents y fragments, persistencia, usando Retrofit, SQLite y SharedPreferences. 

Al arrancar la aplicación nos mostrará una pantalla de bienvenida, 

La aplicación al arrancar  abrirá la  pantalla principal que tendrá dos fragments. En el superior muestra la lista descargada de la api  (se explica el detalle de cada ítem más
adelante). Y en la pantalla inferior la lista de los “favoritos”.

Los datos se descargarán de la api usando Retrofit en el siguiente endpoint:

https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic
<img width="128" height="250" alt="image" src="https://github.com/user-attachments/assets/12c14f4e-ba05-4cc3-bcda-2fdd32186b18" />


Cada ítem tendrá el nombre del cocktail , la foto  y un icono que permitirá guardar el cocktail como favorito. 
Una vez que se ha guardado como favorito, volviendo a pulsar sobre el icono no modifica la elección. Solamente se podrá eliminar de favoritos desde el fragment de favoritos.

<img width="148" height="241" alt="image" src="https://github.com/user-attachments/assets/92995ab6-006b-460f-86fc-4bd784346a7a" />


En el fragment de favoritos, los elementos son editables, al pulsar sobre el elemento, se abrirá una pantalla (puedes elegir si fragment o intent) que permitirá eliminar el elemento de “favoritos”, al guardar la elección volverá a la pantalla principal y debe dejar de mostrarse el elemento en el fragment de favoritos y el icono de favorito en la pantalla superior.

<img width="145" height="314" alt="image" src="https://github.com/user-attachments/assets/2703aa50-32fd-405e-a958-82004756534c" />


En la barra de herramientas añade un botón, que permitirá cambiar el tamaño de la letra.
Esta configuración debe ser persistente. Es  decir, si se cierra la app, al volver a abrirla debe  usar el tamaño de letra que se había elegido anteriormente.

<img width="214" height="82" alt="image" src="https://github.com/user-attachments/assets/87fb2783-ca45-4abf-9f62-27d509a164b8" />
