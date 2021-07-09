<template>
  <div>
    <ion-list>
      <to-do-list-item
        :todoItem="todoItem"
        v-bind:key="todoItem"
        v-for="todoItem in todoItems"
      ></to-do-list-item>
    </ion-list>
    <ion-fab slot="fixed" vertical="bottom" horizontal="end">
      <ion-fab-button router-link="/tabs/overview/newTodo" >
        <ion-icon :icon="add"></ion-icon>
      </ion-fab-button>
    </ion-fab>
  </div>
</template>

<script lang="ts">
import ToDoListItem from "@/components/ToDoListItem.vue";
import { IonList, IonFab, IonFabButton, IonIcon } from "@ionic/vue";
import { defineComponent } from "vue";
import { add } from "ionicons/icons";

export default defineComponent({
  name: "ToDoList",
  components: {
    IonList,
    ToDoListItem,
    IonFab,
    IonFabButton,
    IonIcon,
  },

  props: ["todoItems"],
  setup() {
    return {
      add,
    };
  },
  data(){
return {
  }
  },
});
</script>

<style scoped>
</style>