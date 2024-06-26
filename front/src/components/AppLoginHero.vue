<template>
    <div class="hero">
        <div class="hero-content flex-col lg:flex-row-reverse">
            <div class="text-center lg:text-left">
                <h1 class="text-5xl font-bold">Login now!</h1>
                <p class="py-6">Connect to the administration dashboard.</p>
            </div>
            <div class="card shrink-0 w-full max-w-sm shadow-2xl bg-base-100">
                <form @submit="onSubmit" method="post" class="card-body bg-base-200">
                    <div class="form-control">
                        <AppInput v-model="username" placeholder="Your admin username" class="input input-bordered"
                            :label="'Username'"></AppInput>
                    </div>
                    <div class="form-control">
                        <AppInput v-model="password" placeholder="Your password" class="input input-bordered"
                            :label="'Password'" type="password"></AppInput>
                    </div>
                    <div class="form-control mt-6">
                        <AppButton :disabled="isSubmitting" type="submit" class="btn btn-primary text-sm">
                            {{ 'Submit' }}
                        </AppButton>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useForm } from 'vee-validate';
import { toTypedSchema } from '@vee-validate/yup';
import { object, string } from 'yup';
import { useAuthenticationStore } from '@/stores/authentication.ts';
import AppInput from '@/components/AppInput.vue'
import AppButton from '@/components/AppButton.vue'

const authenticationStore = useAuthenticationStore();

const schema = toTypedSchema(
    object({
        username: string().required().min(1).max(255).default(''),
        password: string().required().min(1).max(255).default(''),
    }),
);
const { handleSubmit, isSubmitting, defineField } = useForm({
    validationSchema: schema,
});
const [username] = defineField('username');
const [password] = defineField('password');

const onSubmit = handleSubmit(async (values, { resetForm }) => {
    const data = new FormData();
    data.append('username', values.username);
    data.append('password', values.password);
    data.append('scope',
        'admin');
    const response = await authenticationStore.loginUser(data);
    resetForm();
    return response;
});
</script>
