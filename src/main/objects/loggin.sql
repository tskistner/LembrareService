delimiter //

drop procedure if exists loggin; //
create procedure loggin (ds_cpf_p text, ds_senha_p text, out id_usuario_p integer, out nm_usuario_p text, out ds_erro_p text)
begin

	select ifnull(max(id_usuario),0), nm_pessoa
	into   id_usuario_p, nm_usuario_p
	from   usuario
	where  ds_cpf = ds_cpf_p
	and    (((ie_usar_senha = 'S') and (ds_senha = ds_senha_p)) or (ie_usar_senha = 'N'));

	if (id_usuario_p = 0) then
		set ds_erro_p = '<p>CPF ou senha incorretos.</p><p>Por favor, tente novamente</p>';
	else
		set ds_erro_p = '';
	end if;
   
end; //