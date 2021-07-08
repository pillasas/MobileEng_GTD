<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Übersicht</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <ion-segment value="time">
        <ion-segment-button @click="loadTimedTodos" value="time"
          >Anstehend</ion-segment-button
        >
        <ion-segment-button @click="loadPrioritisedTodos" value="prioritised"
          >Priorisiert</ion-segment-button
        >
      </ion-segment>
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
  IonSegment,
  IonSegmentButton,
} from "@ionic/vue";
import ToDoList from "@/components/ToDoList.vue";
import { useTodos } from "@/composables/useTodos";
import { defineComponent } from "@vue/runtime-core";

export default defineComponent({
  name: "Overview",
  components: {
    IonContent,
    IonPage,
    IonHeader,
    IonToolbar,
    IonTitle,
    IonSegment,
    IonSegmentButton,
    ToDoList,
  },
  mounted() {
    this.getTodosByTime();
  },
  setup() {
    const { todos, getTodosByTime, getTodosByPriority } = useTodos();
    return { todos, getTodosByTime, getTodosByPriority };
  },
  methods: {
    loadPrioritisedTodos() {
      this.getTodosByPriority();
    },

    loadTimedTodos() {
      this.getTodosByTime();
    },
  },
});
</script>