<script>
	import Task from "./Task.svelte";

	let tasks = [
		{
			state: "todo",
			description: "Prototypes",
		},
		{
			state: "todo",
			description: "Testing",
		},
		{
			state: "inprogress",
			description: "Mockups",
		},
		{
			state: "done",
			description: "User research",
		},
		{
			state: "done",
			description: "Interviews",
		},
		{
			state: "done",
			description: "Sketches",
		},
	];

	let task = "";

	function addTask() {
		tasks = [...tasks, { state: "todo", description: task }];
	}
</script>

<div class="container mt-5">
	<div class="row">
		<div class="col"><h3>Todo</h3></div>
		<div class="col"><h3>In Progress</h3></div>
		<div class="col"><h3>Done</h3></div>
	</div>
	<div class="row">
		<div class="col">
			{#each tasks as task}
				{#if task.state === "todo"}
					<Task bind:task />
					
				{/if}
			{/each}
		</div>
		<div class="col">
			{#each tasks as task}
				{#if task.state === "inprogress"}
					<Task bind:task />
				{/if}
			{/each}
		</div>
		<div class="col">
			{#each tasks as task}
				{#if task.state === "done"}
					<Task bind:task />
				{/if}
			{/each}
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label class="form-label" for="task">Add a new task:</label>
			<input class="form-control" bind:value={task} id="task" type="text" />
			<button class="btn btn-primary" on:click={addTask}>Add task</button>
		</div>
	</div>
</div>

<style>

</style>