# Calculadora personalizada: Conversor de unidades

Para esta práctica he elegido la temática A, el conversor de unidades.

La app convierte km y millas, Celsius y Fahrenheit, y kilos y libras, según lo que se elija en el usuario.

La validación la hago dentro del botón Calcular, antes de hacer ninguna cuenta.

Primero miro si el campo está vacío y, si lo está, muestro un error con setError().

Después paso el texto a número y, si falla, capturo la NumberFormatException y aviso al usuario.

Por último compruebo que el valor tenga sentido: sin distancias ni pesos negativos y sin bajar del cero absoluto.

El cálculo está en un método aparte y el resultado sale con 2 decimales y su unidad.
