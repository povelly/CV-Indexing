<template>
  <div id="app">
    <div class="container">
      <!--UPLOAD-->
      <form enctype="multipart/form-data" novalidate v-if="isInitial || isSaving">
        <h1>Soumission d'un CV</h1>
        <div class="dropbox">
          <p>
            NOM 
          </p>
          <p> 
            <input type="text" name="nom" ref="nom" />
          </p>
          <p>
            Prénom  
          </p>
          <p> 
            <input type="text" name="prenom" ref="prenom" />
          </p>
          <input type="file" multiple  :name="uploadFieldName" :disabled="isSaving" @change="filesChange($event.target.name, $event.target.files); fileCount = $event.target.files.length"
            accept=".pdf, .doc, .docx, image/*" class="input-file">
            <p v-if="isInitial">
              Déposez votre CV <br> ou cliquez pour selectionner
            </p>
            <p v-if="isSaving">
              Uploading {{ fileCount }} files...
            </p>
        </div>
      </form>

      <!-- UPLOAD2 -->
      <!--
      <div class="container">
        <div class="large-12 medium-12 small-12 cell">
          <label>File
            <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
          </label>
            <button v-on:click="submitFile()">Submit</button>
        </div>
      </div>
      -->
      
      <!-- multiple -->
      <!--SUCCESS-->
      <div v-if="isSuccess">
        <h2>Uploaded {{ uploadedFiles.length }} file(s) successfully.</h2>
        <p>
          <a href="javascript:void(0)" @click="reset()">Upload again</a>
        </p>
        <ul class="list-unstyled">
          <li v-for = "item in uploadedFiles" :key = item.url>  <!-- Attention: Peut ne pas marcher  -->
            <img :src="item.url" class="img-responsive img-thumbnail" :alt="item.originalName">
          </li>
        </ul>
        <!-- Ici a été supprimé du code  -->
        
      </div>
      <!--FAILED-->
      <div v-if="isFailed">
        <h2>Uploaded failed.</h2>
        <p>
          <a href="javascript:void(0)" @click="reset()">Nouvel essai</a>
        </p>
        <pre>{{ uploadError }}</pre>
      </div>

      <!--Recherche OK -->
      <div v-if="isRecherche">
        <h2>Recherche effectuée.</h2>
        <p>
          <a href="javascript:void(0)" @click="reset()">Nouvelle Recherche</a>
        </p>
         <div>
             <div v-for="(p, id) in result" :key="`p-${id}`" style="background: cyan; margin: 5px;">
               <h5>{{ p.prenom }} {{ p.nom }}</h5>
               <p>Tel: {{ p.tel }}</p>
               <p>Mail: <a v-bind:href="'mailto:' + p.mail">{{ p.mail }}</a></p>
               <p>Git: <a v-bind:href="'https://' + p.git">{{ p.git }}</a></p>
             </div>
           </div>
      </div>

      <form v-on:submit.prevent="requete" >
        <h1>Recherche des CV ayant les compétences suivante:</h1>
        <input type="text" name="qwery" ref="query" v-on:change="requete()" />
        <input type="submit" value="Recherche" />
      </form>
    </div>
  </div>
</template>

<script>
  import { upload } from './service.js'; //real service
  import { queryf } from './service.js'; //real service
  //import { queryf } from './fake_service2.js'; // fake service
  //import { upload } from './fake_service.js'; // fake service
  //import * as axios from 'axios';

  const STATUS_INITIAL = 0, STATUS_SAVING = 1, STATUS_SUCCESS = 2, STATUS_FAILED = 3, STATUS_RECHERCHE = 4;

  export default {

    //submit files

    name: 'app',
    data() {
      return {
        file:'',
        result:null,
        prenom:null,
        nom: null,
        query: null,
        uploadedFiles: [],
        uploadError: null,
        currentStatus: null,
        uploadFieldName: 'photos'
      }
    },
    computed: {
      isInitial() {
        return this.currentStatus === STATUS_INITIAL;
      },
      isSaving() {
        return this.currentStatus === STATUS_SAVING;
      },
      isSuccess() {
        return this.currentStatus === STATUS_SUCCESS;
      },
      isFailed() {
        return this.currentStatus === STATUS_FAILED;
      },
      isRecherche() {
        return this.currentStatus === STATUS_RECHERCHE;
      }
    },
    methods: {  

      /*submitFile(){
        let formData = new FormData();
        formData.append('file', this.file);
        axios.post( 'http://localhost:8080/upload',
        formData,
                {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
              }
        ).then(function(){
        console.log('SUCCESS!!');
        })
        .catch(function(){
          console.log('FAILURE!!');
        });
      },
      */
      /*
        Handles a change on the file upload
      */
      /*
      handleFileUpload(){
        this.file = this.$refs.file.files[0];
      },
      */

      requete() {
        this.query = this.$refs.query.value;
        const arg = this.query;
        queryf(arg)
        .then(x => {
          //this.result = x; //Liste ici
          //console.log("debug")
          //console.log("x = " + x); // x = [object Object],[object Object]

          this.result = x.data;
          //console.log("result = " + this.result); // result = undefined
          //alert(this.result);
          //alert(this.result.prenom);

          this.currentStatus = STATUS_RECHERCHE;
        })
        .catch((err) => {
    if (err.response) {
      console.log("ERREUR GET :");
      console.log(">> " + err.response.data);
      console.log(">> " + err.response.status);
      console.log(">> " + err.response.headers);
      this.uploadError = err.response;
      this.currentStatus = STATUS_FAILED;
    }
  })
      },

      resToString(l) {
                  let res = "";
                  l.forEach((p) => {
                    res += p.prenom + " "+ p.nom + " Tel : " + p.tel + " Mail : " + p.mail + " Git : " + p.git;
                  });
                  return res;
                },

      reset() {
        // reset form to initial state
        this.currentStatus = STATUS_INITIAL;
        this.result = null;
        this.prenom = null;
        this.nom = null;
        this.uploadedFiles = [];
        this.uploadError = null;
      },
      save(formData) {
        // upload data to the server
        this.currentStatus = STATUS_SAVING;

        upload(formData)
          .then(x => {
            this.uploadedFiles = [].concat(x);
            this.currentStatus = STATUS_SUCCESS;
          })
          .catch(err => {
            this.uploadError = err.response;
            this.currentStatus = STATUS_FAILED;
          });
      },
      filesChange(fieldName, fileList) {
        // handle file changes
        const formData = new FormData();
        

        if (!fileList.length) return;

        // append the files to FormData
        Array
          .from(Array(fileList.length).keys())
          .map(x => {
            this.nom = this.$refs.nom.value.toUpperCase();
            this.prenom = this.$refs.prenom.value.toLowerCase().replace(/^\w/, (c) => c.toUpperCase());
            //alert (this.nom);
            //alert(fileList[x].name); //Ici changer valeur
            let parts = fileList[x].name.split('.');
            let extension = parts[parts.length -1 ];
            //alert(parts);
            formData.append(fieldName, fileList[x], (this.nom + "_" + this.prenom + "." + extension));
          });

        // save it
        this.save(formData);
      }
    },
    mounted() {
      this.reset();
    },
  }

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
