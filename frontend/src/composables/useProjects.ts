import { getAllProjects, getProject } from "@/api/projects";
import { Project } from "@/model/project";
import { onMounted, ref } from "vue";

export function useProjects() {

    const projects = ref<Project[]>([]);

    const editProject = ref<Project>({});

    const getProjects = async () => {
        try {
            projects.value = await getAllProjects();
        } catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    const getProjectById = async (id: number) => {
        try {
            editProject.value = await getProject(id);
        }
        catch (error) {
            console.log(error); // FIXME: Errorhandling
        }
    }

    onMounted(getProjects);

    return {
        projects,
        editProject,
        getProjects,
        getProjectById

    }
    
}


