import { getAllToDos, updateToDo, addNewToDo, deleteTodo, getTodo } from '@/api/todos';
import { ToDo } from '@/model/todo';
import { onMounted, ref } from 'vue';

export function useTodos() {

    const todos = ref<ToDo[]>([]);

    const editTodo = ref<ToDo>({});

    const deletedTodo = ref<ToDo>({});

    const getTodos = async () => {
        try {
            todos.value = await getAllToDos();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const getTodoByTodoId = async (id: number) => {
        try {
            editTodo.value = await getTodo(id);
        }
        catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const getTodosByTime = async () => {
        try {
            const t = await getAllToDos();
            todos.value = t.filter((todo) => {
                // Workaround TypeCast, da aus unerklärlichen Gründen mit ToDo nicht funktioniert.
                const tmp = todo as any;
                if (tmp.zeitpunkt) {
                    return true;
                }
                return false;
            })
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const getTodosByPriority = async () => {
        try {
            const t = await getAllToDos();
            todos.value = t.filter((todo) => {
                // Workaround TypeCast, da aus unerklärlichen Gründen mit ToDo nicht funktioniert.
                const tmp = todo as any;
                if (tmp.priorisierung > 0) {
                    return true;
                }
                return false;
            })
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
            await addNewToDo(editTodo.value);
            getTodos();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const removeTodo = async (todo: ToDo) => {
        try {
            await deleteTodo(todo);
            
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(getTodos);

    return {
        editTodo,
        deletedTodo,
        todos,
        getTodos,
        getTodoByTodoId,
        getTodosByTime,
        getTodosByPriority,
        addTodo,
        finishTodo,
        removeTodo
    }
}