<script>
    export let task;

    function moveTask() {
        if (task.state === "todo") {
            task.state = "inprogress";
        } else if (task.state === "inprogress") {
            task.state = "done";
        }
    }
</script>

<div class={task.state}>
    <div><b>{task.description}</b></div>
    {#if task.state !== "done"}
        <div class="mt-2">
            <button class="btn btn-light" on:click={moveTask}>move right</button>
        </div>
    {/if}
</div>

<style>
    .todo,
    .inprogress,
    .done {
        border: 1px black solid;
        border-radius: 8px;
        margin-bottom: 1em;
        padding: 10px;
        height: 6em;
    }

    .todo {
        background-color: lightblue;
    }

    .inprogress {
        background-color: lightsalmon;
    }

    .done {
        background-color: lightgreen;
    }
</style>
