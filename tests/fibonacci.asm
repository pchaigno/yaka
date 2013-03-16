; entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586

.CODE

fibonacci :

; ouvbloc 0
enter 0, 0

; iload 4
push word ptr [bp+4]

; iconst 2
push word ptr 2

; iinf
pop bx
pop ax
cmp ax, bx
jge $+6
push -1
jmp $+4
push 0

; iffaux SINON1
pop ax
cmp ax, 0
je SINON1

; iload 4
push word ptr [bp+4]

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

; call fibonacci
call fibonacci

; reserveRetour
sub sp, 2

; iload 4
push word ptr [bp+4]

; iconst 2
push word ptr 2

; isub
pop bx
pop ax
sub ax, bx
push ax

; call fibonacci
call fibonacci

; iadd
pop bx
pop ax
add ax, bx
push ax

; ireturn 6
pop ax
mov [bp+6], ax

FSI1 :

; fermebloc 2
leave
ret 2

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

; call fibonacci
call fibonacci

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
