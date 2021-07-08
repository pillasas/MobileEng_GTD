<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Alle Aufgaben</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <to-do-list v-bind:todoItems="todos"></to-do-list>
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
import { useTodos } from "@/composables/useTodos";
import { defineComponent } from "@vue/runtime-core";

export default defineComponent({
  name: "AllTasks",
  components: {
    IonContent,
    IonPage,
    IonHeader,
    IonToolbar,
    IonTitle,
    ToDoList,
  },
  mounted() {
    this.getTodos();
  },
  setup() {
    const { todos, getTodos} = useTodos();
    return { todos, getTodos};
  },
});
</script>