
# MyStudioFactory

Cette application a été réalisée sur mon temps libre, dans le but d'une test technique pour la societe MyStudioFactory.

Le source des données se trouve sur https://dev.mystudiofactory.com/trombi

#À propos du développeur ::

Si vous avez des questions concernant l'application, des bugs à remonter, des suggestions ou remarques, n'hésitez pas à me contacter sur :

Mail: benabdallahismail121@gmail.com

Linkedin: https://www.linkedin.com/in/baismail


## Architecture ::
Clean Architecture : Elle réduire les dépendances de logique métier avec les services que l'app consomme (API, Base de données, Framework, Librairies tierces), et pour maintenir une application stable au cours de ses évolutions, de ses tests mais également lors de changements ou mises à jour des ressources externes.

Coroutines Kotlin  : Elle fournissent une API qui vous permez d'écrire du code asynchrone. Avec les coroutines Kotlin, vous pouvez définir unCoroutineScope  qui vous aide à gérer quand vos coroutines doivent s'exécuter. Chaque opération asynchrone s'exécute dans une étendue particulière.

Koin               : Pour l'injection de dépendance, il est léger et pragmatique pour les développeurs Kotlin. C'est très simple pour l'utilisation par rapport autre libraire d'injection de dépendance.

Retrofit           : C'st un client HTTP de type sécurisé pour Android. Retrofit facilite la connexion à un service Web REST en traduisant l'API en interfaces. Elle est puissante facilite la consommation de données JSON ou XML, qui sont ensuite analysées en objets. Les requêtes GET, POST, PUT, PATCH et DELETE peuvent toutes être exécutées. Retrofit utilise #OkHttp (du même développeur) pour gérer les requêtes réseau. De plus, Retrofit utile le convertisseur #Gson pour analyser les objets JSON.

Timber             : Parmi les problèmes avec le mécanisme de journalisation par défaut est que vous devez passer le TAG à chaque fois que vous écrivez une instruction de journal. Mais avec Timber il rende l'utilisation de log plus simple.

Glide              : Elle est rapide et efficace pour Android axée sur un défilement fluide. Glide propose une API facile à utiliser. Glide prend en charge la récupération, le décodage et l'affichage d'images fixes vidéo, d'images et de GIF animés. Glide rendre le défilement de toute sorte d'une liste d'images aussi fluide et rapide que possible, elle est également efficace dans tous les cas où vous devez récupérer, redimensionner et afficher une image distante.


# Officiel site pour Android & Architecture Components ::
https://developer.android.com/jetpack/docs/guide

# Open Source Libraries ::
Kotlin      : https://kotlinlang.org
Gson        : https://github.com/google/gson
Retrofit    : https://square.github.io/retrofit
OkHttp      : https://square.github.io/okhttp
Koin        : https://github.com/InsertKoinIO/koin
Coroutines  : https://github.com/Kotlin/kotlinx.coroutines
Timber      : https://github.com/JakeWharton/timber
Glide       : https://bumptech.github.io/glide/



### Quelques screenshots ::
<p float="center">
<img src="/screenshots/list_user_screen.jpg" width="40%">
<img src="/screenshots/details_user_screen.jpg" width="40%">
</p>



#### The MIT License

#### Copyright (c) 2020 BENABDALLAH Ismail, https://www.linkedin.com/in/baismail

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

