; entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

factorial :

; ouvbloc 0
enter 0, 0

; iload 4
push word ptr [bp+4]

; iconst 1
push word ptr 1

; iegal
pop bx
pop ax
cmp ax, bx
jne $+6
push -1
jmp $+4
push 0

; iffaux SINON1
pop ax
cmp ax, 0
je SINON1

; iconst 1
push word ptr 1

; ireturn 6
pop ax
mov [bp+6], ax

; goto FSI1
jmp FSI1

SINON1 :

; reserveRetour
sub sp, 2

; iload 4
push word ptr [bp+4]

; iconst 1
push word ptr 1

; isub
pop bx
pop ax
sub ax, bx
push ax

; call factorial
call factorial

; iload 4
push word ptr [bp+4]

; imul
pop bx
pop ax
imul bx
push ax

; ireturn 6
pop ax
mov [bp+6], ax

FSI1 :

; fermebloc 2
leave
ret 2

factorialtailrecursive :

; ouvbloc 0
enter 0, 0

; iload 6
push word ptr [bp+6]

; iconst 1
push word ptr 1

; iegal
pop bx
pop ax
cmp ax, bx
jne $+6
push -1
jmp $+4
push 0

; iffaux SINON2
pop ax
cmp ax, 0
je SINON2

; iload 4
push word ptr [bp+4]

; ireturn 8
pop ax
mov [bp+8], ax

; goto FSI2
jmp FSI2

SINON2 :

; reserveRetour
sub sp, 2

; iload 6
push word ptr [bp+6]

; iconst 1
push word ptr 1

; isub
pop bx
pop ax
sub ax, bx
push ax

; iload 4
push word ptr [bp+4]

; iload 6
push word ptr [bp+6]

; imul
pop bx
pop ax
imul bx
push ax

; call factorialtailrecursive
call factorialtailrecursive

; ireturn 8
pop ax
mov [bp+8], ax

FSI2 :

; fermebloc 4
leave
ret 4

debut :
STARTUPCODE

main :

; ouvbloc 4
enter 4, 0

; lireEnt -2
lea dx, [bp-2]
push dx
call lirent

; aLaLigne
call ligsuiv

; reserveRetour
sub sp, 2

; iload -2
push word ptr [bp-2]

; call factorial
call factorial

; istore -4
pop ax
mov word ptr [bp-4], ax

; aLaLigne
call ligsuiv

; iload -4
push word ptr [bp-4]

; ecrireEnt
call ecrent

; aLaLigne
call ligsuiv

; lireEnt -2
lea dx, [bp-2]
push dx
call lirent

; aLaLigne
call ligsuiv

; reserveRetour
sub sp, 2

; iload -2
push word ptr [bp-2]

; iconst 1
push word ptr 1

; call factorialtailrecursive
call factorialtailrecursive

; istore -4
pop ax
mov word ptr [bp-4], ax

; aLaLigne
call ligsuiv

; iload -4
push word ptr [bp-4]

; ecrireEnt
call ecrent

; queue
nop
EXITCODE
end debut
