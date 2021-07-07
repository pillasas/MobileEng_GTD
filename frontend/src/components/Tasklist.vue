<template>
  <ion-page>
    <ion-content>
      <ion-list>
        <task-list-item v-bind="todo" v-bind:key="todo" v-for="todo in todos"></task-list-item>
      </ion-list>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { useTodos } from "@/composables/useTodos";
import TaskListItem from "@/components/TasklistItem.vue";
import {
  IonContent,
  IonPage,
  IonList,
  IonHeader
} from "@ionic/vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "Tasklist",
  components: {
    IonContent,
    IonPage,
    IonList,
    TaskListItem,
  },
  mounted(){
    this.getTodos();
  },
  setup() {
    const { todos, getTodos} = useTodos();
    return { todos, getTodos };
  },
});
</script>

<style scoped>
</style>