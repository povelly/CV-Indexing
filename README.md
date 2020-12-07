#FRONTEND

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

Fonctionnement de l'application:
Lancer le backend (Spring)
Lancer le front (Vue.js)

Lors du lancement, le client s'afficher sur le port http://localhost:8090/

Pour ajouter un CV:
Renseignez votre nom et prénom dans les champs associés (Les majuscules n'ont pas d'importance)
Ajoutez un CV de la CVthèque fournie (ou non).
L'opération doit retourner "Succès".

Pour rechercher dans la base de donnée les CVs qui possèdent des compétences précises:
Renseignez chacune des compétences nécéssaires, séparés par un virgule "," et un espace " ".
Cliquez ensuite sur "Rechercher".
L'opération retourne la Liste des CVs qui mentionnent les compétences recherchés. 


Description du FrontEnd:
Le fichier App.js contient:
- La partie html de l'application
- La partie Script en js avec les différentes méthodes appelés
- Un css basique

Le fichier service.js contient les requêtes POST et GET.

Le fichier fake-service permet de tester le front sans le backend. 

#BACKEND

Executer le main : 
```
src.main.java.com.cvservice.CVMicroservice.java
```

Il faut ajouter Appache.tika (jar présent dans la racine du projet).


#Utiles

Des CV sont dans la cvtheque au besoin.

Pour ajouter des skills détectables, ajouter à la liste skills de la classe com.cvservice.parser.RegEx.java le nom de votre skill.

Le RegEx est généré automatiquement.

#Commande Front

Trouver les candidats qui possèdent certains skills (pas de nombre limite de skills par commande): 
```
skill1,skill2,skill3
```
Trouver un candidat via sont telephone (identifiant du model candidate)
```
Phone NUMERODETELSANSSEPARATEURS
```



