<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
          
        <ion-title>Projekte</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <ion-list>
        <ion-item       button
      v-bind:router-link="'/tabs/projects/' + project.id"
       v-bind:key="project" v-for="project in projects" >{{
          project.title
        }}</ion-item>
      </ion-list>
      <ion-fab slot="fixed" vertical="bottom" horizontal="end"> </ion-fab>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { IonList, IonFab, IonItem, IonPage, IonHeader, IonToolbar, IonTitle, IonContent } from "@ionic/vue";
import { defineComponent } from "vue";
import { useProjects } from "@/composables/useProjects";


export default defineComponent({
  name: "Projects",
  components: {
    IonList,
    IonFab,
    IonItem,
    IonPage, 
    IonHeader, 
    IonToolbar, 
    IonTitle, 
    IonContent,
  },
  mounted() {
    this.getProjects();
  },
  setup() {
    const { projects, getProjects } = useProjects();
    return { projects, getProjects };
  },
});
</script>

<style scoped>
</style>