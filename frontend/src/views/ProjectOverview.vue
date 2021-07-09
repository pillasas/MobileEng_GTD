<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Aufgaben des Projekts {{editProject.title}}</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <to-do-list v-bind:todoItems="editProject.toDos"></to-do-list>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
} from "@ionic/vue";
import ToDoList from "@/components/ToDoList.vue";
import { useProjects } from "@/composables/useProjects";
import { defineComponent } from "@vue/runtime-core";
import { useRoute } from "vue-router";

export default defineComponent({
  name: "ProjectOverview",
  components: {
    IonContent,
    IonPage,
    IonHeader,
    IonToolbar,
    IonTitle,
    ToDoList,
  },
    mounted() {
    this.getProjectById(Number(this.id));
  },
  setup() {
    const route = useRoute();
    const { editProject, getProjectById } = useProjects();
    const { id } = route.params;
    return { id, editProject, getProjectById };
  },
});
</script>