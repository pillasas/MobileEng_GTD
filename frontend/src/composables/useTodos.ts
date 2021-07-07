import { getAllToDos, updateToDo, addNewToDo, deleteTodo } from '@/api/todos';
import { ToDo } from '@/model/todo';
import { onMounted, ref } from 'vue';

export function useTodos() {

    const todos = ref<ToDo[]>([]);

    const newTodo = ref<ToDo>({});

    const deletedTodo = ref<ToDo>({});

    const getTodos = async () => {
        try {
            todos.value = await getAllToDos();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const getTodosByTime = async () => {
        try {
            todos.value = await getAllToDos();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const finishTodo = async (todo: ToDo) => {
        try {
            todo.done = true;
            updateToDo(todo);
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const addTodo = async () => {
        try {
            // add the new todo and update the list of all todos afterwards
            await addNewToDo(newTodo.value);
            getTodos();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const removeTodo = async () => {
        try {
            await deleteTodo(deletedTodo.value);
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(getTodos);

    return {
        newTodo,
        deletedTodo,
        todos,
        getTodos,
        addTodo,
        finishTodo,
        removeTodo
    }
}